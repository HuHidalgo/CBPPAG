package com.cenpro.sircie.configuracion;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.cenpro.sircie.service.impl", "com.cenpro.sircie.mapper" })
public class ServiceConfiguration
{

}
