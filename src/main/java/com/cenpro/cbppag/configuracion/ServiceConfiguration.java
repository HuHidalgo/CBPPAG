package com.cenpro.cbppag.configuracion;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.cenpro.cbppag.service.impl", "com.cenpro.cbppag.mapper" })
public class ServiceConfiguration
{

}