package com.example.geekmoney.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.example.geekmoney.config.token.CustomTokenEnhancer;

@Profile("oauth-security")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	/*
	 * Configuração co clente
	 * cliente tem que logar com as credencias descritas
	 * access token valido por 20 segundos
	 * refresh token valido por 1 dia
	 * */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient("angular")
		.secret("angul@r")
		.scopes("read", "write")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(2000)
		.refreshTokenValiditySeconds(3600*24);
	}
	
	/*
	 * endpoints configuration
	 * nao armazenado e sim decodificado
	 * converte declarado como metodo
	 * recuseRefreshTokens -------- LEMBRAR
	 * Manager of authentication declared how authenticationManager
	 * */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhacer(), accessTokenConverter()));
		
		endpoints
		.tokenStore(tokenStore())
		.tokenEnhancer(tokenEnhancerChain)
		.reuseRefreshTokens(false)
		.authenticationManager(authenticationManager);
	}
	

	/*
	 * instance the access token converter
	 * and declare the secret password how geekmoney
	 * */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("geekmoney");
		return accessTokenConverter;
	}

	/*
	 * it define where will be store the token
	 * */
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public TokenEnhancer tokenEnhacer() {
		return new CustomTokenEnhancer();
	}
}
