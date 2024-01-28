package business.datasource.network.main

import business.core.KtorHttpClient
import business.datasource.network.common.JRNothing
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.responses.AddressDTO
import business.datasource.network.main.responses.BasketDTO
import business.datasource.network.main.responses.CommentDTO
import business.datasource.network.main.responses.HomeDTO
import business.datasource.network.main.responses.ProductDTO
import business.datasource.network.main.responses.ProfileDTO
import business.datasource.network.main.responses.SearchDTO
import business.datasource.network.main.responses.SearchFilterDTO
import business.datasource.network.main.responses.WishlistDTO

interface MainService {
    companion object {
        const val SEARCH_FILTER = "search/filter"
        const val SEARCH = "search"
        const val BASKET = "basket"
        const val BASKET_ADD = "basket/add"
        const val BASKET_DELETE = "basket/delete"
        const val HOME = "home"
        const val PRODUCT = "product"
        const val LIKE = "like"
        const val PROFILE = "profile"
        const val COMMENT = "comment"
        const val WISHLIST = "product/wishlist"
        const val ADDRESS = "address"
    }

    suspend fun getAddresses(
        token: String,
    ): MainGenericResponse<List<AddressDTO>>

    suspend fun addAddress(
        token: String,
        address: String,
        city: String,
        country: String,
        state: String,
        zipCode: String,
    ): MainGenericResponse<JRNothing>

    suspend fun getComments(
        token: String,
        id: Int,
    ): MainGenericResponse<List<CommentDTO>>

    suspend fun addComment(
        token: String,
        productId: Int,
        rate: Double,
        comment: String,
    ): MainGenericResponse<JRNothing>

    suspend fun search(
        token: String,
        minPrice: Int?,
        maxPrice: Int?,
        sort: Int?,
        categoriesId: String?,
        page: Int,
    ): MainGenericResponse<SearchDTO>

    suspend fun getSearchFilter(
        token: String,
    ): MainGenericResponse<SearchFilterDTO>

    suspend fun getProfile(token: String): MainGenericResponse<ProfileDTO>
    suspend fun updateProfile(
        token: String,
        name: String,
        age: String,
        image: ByteArray?,
    ): MainGenericResponse<Boolean>

    suspend fun basket(token: String): MainGenericResponse<List<BasketDTO>>
    suspend fun basketAdd(token: String, id: Int, count: Int): MainGenericResponse<JRNothing?>
    suspend fun basketDelete(token: String, id: Int): MainGenericResponse<JRNothing?>
    suspend fun home(token: String): MainGenericResponse<HomeDTO>
    suspend fun product(token: String, id: Int): MainGenericResponse<ProductDTO>
    suspend fun like(token: String, id: Int): MainGenericResponse<JRNothing?>
    suspend fun wishlist(
        token: String,
        categoryId: Int?,
        page: Int
    ): MainGenericResponse<WishlistDTO>


    object Factory {
        fun build(): MainService {
            return MainServiceImpl(
                httpClient = KtorHttpClient.httpClient()
            )
        }
    }


}