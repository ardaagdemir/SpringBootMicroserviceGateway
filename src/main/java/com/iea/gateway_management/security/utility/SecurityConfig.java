package com.iea.gateway_management.security.utility;

import com.iea.gateway_management.security.jwt.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    // ***2 -> CustomUserDetailsService
    @Autowired
    private UserDetailsService userDetailsService;

    // ***10 -> AbstractAuthenticationService
    /*
        kaynaklar arası paylaşım için
        CORS (cross origin resource sharing) politikası

        CORS konfigürasyonu ile
            izin verilen kaynaklar,
            izin verilen metotlar,
            izin verilen yolları belirleyeceğiz.

        örnek: Burası sayesinde, uygulamaya yalnızca GET isteği izni verilebilir.
                Belirli bir alan adı varsa, hostlar yalnızca ona gore kısıtlanabilir.

        CORS tarafından bir isteğe izin verilmiyorsa, istek engellenir.

        Bu bir Java bean (bkz. @Bean annotation) olduğu için,
        bundan yeni nesneler oluşturulup (bkz. dependency injection)
        tüm uygulama düzeyinde kullanılabilir.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addCorsMappings(CorsRegistry registry)
            {
                registry.addMapping("/**")
                        .allowedOrigins("*") // or: localhost
                        .allowedMethods("*"); // or: POST, GET etc.
            }
        };

    }

    // ***6 -> JWTAuthorizationFilter
    @Bean
    private JWTAuthorizationFilter createJWTAuthorizationFilter()
    {
        return new JWTAuthorizationFilter();
    }

    // ***5 -> createJWTAuthorizationFilter()
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.csrf().disable();

        /*
          SessionCreationPolicy.ALWAYS -> session yoksa mutlaka olusturur.

          SessionCreationPolicy.NEVER -> Framework, yeni bir session hic bir zaman olusturmaz.
          Ne var ki, eger halihazirda varsa, onu kullanir.

          SessionCreationPolicy.IFREQUIRED -> Gerekliyse oturum olusturur. (varsayilan hal bu)

          SessionCreationPolicy.STATELESS -> Framework, yeni bir session hic bir zaman olusturmaz ve kullanmaz.
         */
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        /*
           istemciler, web sunucusu tarafindan desteklenen istekleri ogrenmek icin
           OPTION istegi yollar.
           Burada, web sunucusu tarafindan desteklenen isteklere izin veriyoruz.
         */
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/api/authentication/**").permitAll()
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(createJWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    // ***1 -> yukarıda userDetailsService
    // kimlik doğrulama (authentication) ile ilgili
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(createPasswordEncoder());
    }

    @Bean
    private PasswordEncoder createPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
