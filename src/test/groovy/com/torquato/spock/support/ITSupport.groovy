package com.torquato.spock.support

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.codehaus.groovy.runtime.NullObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
class ITSupport extends Specification {

    @Autowired
    MockMvc mvc
    @Autowired
    ObjectMapper objectMapper
    @Autowired
    MongoTemplate mongoTemplate

    def setupSpec() {
        NullObject.metaClass.toString = { '' }
    }

    List getList(String path, HttpStatus expectedStatus) {
        objectMapper.readValue(_get(path, expectedStatus), new TypeReference<List>() {})
    }

    Object get(String path, Class<?> resultClass, HttpStatus expectedStatus) {
        objectMapper.readValue(_get(path, expectedStatus), resultClass)
    }

    byte[] _get(String path, HttpStatus expectedStatus) {
        mvc.perform(MockMvcRequestBuilders.get(path)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(expectedStatus.value()))
                .andReturn()
                .getResponse()
                .getContentAsByteArray()
    }

}
