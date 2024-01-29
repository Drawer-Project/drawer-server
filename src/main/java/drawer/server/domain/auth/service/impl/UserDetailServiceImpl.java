package drawer.server.domain.auth.service.impl;

import drawer.server.common.error.GlobalErrorCode;
import drawer.server.common.error.GlobalException;
import drawer.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 해당 메서드는 {@link drawer.server.common.security.jwt.JwtTokenManager}클래스에서 호출되어 {@link
     * drawer.server.common.security.jwt.JwtAuthenticationFilter}에서 사용된다. service 클래스 자체의 정의는 auth 도메인
     * 내에서 진행하지만 사용처는 {@link jakarta.servlet.FilterChain} 의 필터링 과정에서 사용되므로 global exception을 발생시킨다.
     */
    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.AUTHENTICATION_FAIL_ERROR));
    }
}
