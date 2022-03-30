package com.trusov.collapsingtoolbarviewtest.data.remote.retrofit

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ApiTest {

    private lateinit var service: ApiService
    private lateinit var coroutineScope: CoroutineScope

    @Before
    fun setUp() {
        service = ApiFactory.service
        coroutineScope = CoroutineScope(Dispatchers.IO)
    }

    @Test
    fun imageIsNotNull() {
        coroutineScope.launch {
            val image = service.getImageById(1)
            assertNotEquals(image, null)
        }
    }

    @Test
    fun imageUrlEqualsExpectedUrl(){
        coroutineScope.launch {
            val expectedUrl = "https://via.placeholder.com/150/92c952"
            val image = service.getImageById(1)
            assertEquals(image.smallImageUrl, expectedUrl)
        }
    }

    @Test
    fun listOfFoodItemsIsNotEmpty() {
        coroutineScope.launch {
            val list = service.getListOfFoodItems()
            assertNotEquals(list.size, 0)
        }
    }

    @After
    fun close() {
        coroutineScope.cancel()
    }
}