package com.trusov.collapsingtoolbarviewtest.domain.use_case

import com.trusov.collapsingtoolbarviewtest.domain.entity.Category
import com.trusov.collapsingtoolbarviewtest.domain.repository.ShopRepository
import javax.inject.Inject

class GetListOfCategoriesUseCase @Inject constructor(
    private val repository: ShopRepository
) {
    suspend operator fun invoke(): List<Category> {
        return repository.getListOfCategories()
    }
}