package eu.codlab.tcg.pricing.requests

import eu.codlab.http.createClient
import eu.codlab.tcg.pricing.models.Category
import eu.codlab.tcg.pricing.models.Group
import eu.codlab.tcg.pricing.models.Price
import eu.codlab.tcg.pricing.models.Product
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

object RequestLoader {

    private val client = createClient { }

    private val json = Json {
        ignoreUnknownKeys = true
    }

    suspend fun products(categoryId: Int, groupId: Int): List<Product> =
        load(categoryId, groupId, "products", Product.serializer())

    suspend fun prices(categoryId: Int, groupId: Int): List<Price> =
        load(categoryId, groupId, "prices", Price.serializer())

    suspend fun groups(categoryId: Int): List<Group> =
        load(Group.serializer()) { "https://tcgcsv.com/$categoryId/groups" }

    suspend fun categories(): List<Category> =
        load(Category.serializer()) { "https://tcgcsv.com/categories" }

    private suspend fun <T> load(
        categoryId: Int,
        groupId: Int,
        suffix: String,
        serializer: KSerializer<T>
    ): List<T> = load(serializer) { "https://tcgcsv.com/$categoryId/$groupId/$suffix" }

    private suspend fun <T> load(kSerializer: KSerializer<T>, buildUrl: () -> String): List<T> {
        val url = buildUrl()
        val request = client.get(url)

        if (!request.status.isSuccess()) {
            throw IllegalStateException("Couldn't load $url")
        }

        val answer: Answer<T> =
            json.decodeFromString(Answer.serializer(kSerializer), request.bodyAsText())

        return answer.results
    }
}
