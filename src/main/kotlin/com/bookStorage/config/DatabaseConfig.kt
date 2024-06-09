package com.bookStorage.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import javax.sql.DataSource

@Configuration
class DatabaseConfig(var env: Environment) {

    fun dataSource(){

    }
}