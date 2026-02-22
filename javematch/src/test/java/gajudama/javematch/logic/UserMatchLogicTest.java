package gajudama.javematch.logic;

import gajudama.javematch.accesoDatos.UserMatchRepository;
import gajudama.javematch.model.UserMatch;
import gajudama.javematch.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserMatchLogicTest {

    @Mock
    private UserMatchRepository matchRepository;

    @Mock
    private UsuarioLogic usuarioLogic;

    @InjectMocks
    private UserMatchLogic userMatchLogic;

    @Test
    public void createMatchReusesExistingMutualMatch() {
        Long userId = 1L;
        Long likedUserId = 2L;
        UserMatch existing = new UserMatch();
        existing.setUserMatchId(10L);
        existing.setFechaMatch(new java.util.Date());

        when(matchRepository.findByUsers(userId, likedUserId)).thenReturn(Optional.of(existing));

        UserMatch result = userMatchLogic.createMatch(userId, likedUserId);

        assertSame(existing, result);
        verify(matchRepository, never()).save(any(UserMatch.class));
    }

    @Test
    public void createMatchRejectsMatchingYourself() {
        Long userId = 1L;

        when(matchRepository.findByUsers(userId, userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userMatchLogic.createMatch(userId, userId));
    }

    @Test
    public void createMatchThrowsWhenLikedUserDoesNotExist() {
        Long userId = 1L;
        Long likedUserId = 2L;
        Usuario user = new Usuario();
        user.setUserId(userId);

        when(matchRepository.findByUsers(userId, likedUserId)).thenReturn(Optional.empty());
        when(usuarioLogic.getUsuarioById(userId)).thenReturn(Optional.of(user));
        when(usuarioLogic.getUsuarioById(likedUserId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userMatchLogic.createMatch(userId, likedUserId));
    }

    @Test
    public void createMatchPersistsNewMatch() {
        Long userId = 1L;
        Long likedUserId = 2L;
        Usuario user = new Usuario();
        user.setUserId(userId);
        Usuario likedUser = new Usuario();
        likedUser.setUserId(likedUserId);

        UserMatch saved = new UserMatch();
        saved.setUserMatchId(7L);

        when(matchRepository.findByUsers(userId, likedUserId)).thenReturn(Optional.empty());
        when(usuarioLogic.getUsuarioById(userId)).thenReturn(Optional.of(user));
        when(usuarioLogic.getUsuarioById(likedUserId)).thenReturn(Optional.of(likedUser));
        when(matchRepository.save(any(UserMatch.class))).thenReturn(saved);

        UserMatch result = userMatchLogic.createMatch(userId, likedUserId);

        assertSame(saved, result);
        verify(matchRepository).save(argThat(match ->
                match.getUser1() == user && match.getUser2() == likedUser
                        && match.getFechaMatch() != null));
    }

    @Test
    public void updateMatchThrowsWhenMatchDoesNotExist() {
        when(matchRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userMatchLogic.updateMatch(99L, new UserMatch()));
    }

    @Test
    public void getMutualMatchDelegatesToRepository() {
        UserMatch match = new UserMatch();
        match.setUserMatchId(3L);
        when(matchRepository.findMutualMatch(1L)).thenReturn(java.util.Collections.singletonList(match));

        assertTrue(userMatchLogic.getMutualMatch(1L).contains(match));
        verify(matchRepository).findMutualMatch(1L);
    }
}