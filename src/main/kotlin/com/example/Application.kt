package com.example

import com.fasterxml.jackson.databind.ser.Serializers.Base
import io.micronaut.core.annotation.Introspected
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.RequestBean
import io.micronaut.runtime.Micronaut.run

fun main(args: Array<String>) {
    run(*args)
}

@Introspected
data class PathWrapper(@field:PathVariable val path:String)


@Controller("/")
class SimpleController {
    // Produces only one "path" parameter
    @Get("{path}/simple")
    fun getPath(@RequestBean wrapper:PathWrapper) = wrapper.path
}

open class BaseController

@Controller("/inherits")
class SimpleControllerInherits : BaseController() {
    // produces two "path" parameters!!
    @Get("{path}/simple")
    fun getPath(@RequestBean wrapper:PathWrapper) = wrapper.path
}
