package eu.codlab.tcg.pricing

import eu.codlab.tcg.pricing.presets.Categories
import eu.codlab.tcg.pricing.requests.RequestLoader
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class RequestLoaderTest {
    @Test
    fun loadCategories() = runTest {
        val categories = RequestLoader.categories()

        assertNotNull(categories)
        assertTrue(categories.isNotEmpty())
    }

    @Test
    fun loadFromCategoriToProduct() = runTest {
        val testingUsingOneSpecificCategory = Categories.lorcana.first()

        // testing getting all the categories FROM the group where the first "known" group exist in
        val groups = RequestLoader.groups(testingUsingOneSpecificCategory.categoryId)

        assertNotNull(groups)
        assertTrue(groups.isNotEmpty())

        // now get the first item...
        val group = groups.first()

        listOf(
            RequestLoader.products(group.categoryId, group.groupId),
            RequestLoader.prices(group.categoryId, group.groupId)
        ).forEach {
            assertNotNull(it)
            assertTrue(it.isNotEmpty())
        }
    }
}
