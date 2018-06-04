package com.cenpro.cbppag.configuracion;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@ComponentScan(basePackages = { "com.cenpro.cbppag.configuracion"})
public class ApplicationConfiguration
{

}