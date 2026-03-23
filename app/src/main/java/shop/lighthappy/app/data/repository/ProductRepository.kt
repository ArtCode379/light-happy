package shop.lighthappy.app.data.repository

import shop.lighthappy.app.R
import shop.lighthappy.app.data.model.Product
import shop.lighthappy.app.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products: List<Product> = listOf(
        Product(
            id = 1,
            title = "Classic Linen Blazer",
            description = "Elegant linen blazer perfect for summer occasions. Breathable fabric with tailored fit.",
            price = 89.99,
            imageRes = R.drawable.product1,
            category = ProductCategory.CLOTHING,
        ),
        Product(
            id = 2,
            title = "Silk Evening Dress",
            description = "Flowing silk dress with delicate draping. Ideal for cocktail parties and formal events.",
            price = 129.99,
            imageRes = R.drawable.product2,
            category = ProductCategory.CLOTHING,
        ),
        Product(
            id = 3,
            title = "Casual Denim Jacket",
            description = "Timeless denim jacket with a relaxed fit. A wardrobe essential for any season.",
            price = 69.99,
            imageRes = R.drawable.product3,
            category = ProductCategory.CLOTHING,
        ),
        Product(
            id = 4,
            title = "Gold Chain Necklace",
            description = "Minimalist gold-plated chain necklace. Adds a touch of sophistication to any outfit.",
            price = 34.99,
            imageRes = R.drawable.product4,
            category = ProductCategory.ACCESSORIES,
        ),
        Product(
            id = 5,
            title = "Leather Crossbody Bag",
            description = "Compact genuine leather crossbody bag with adjustable strap. Perfect for everyday use.",
            price = 79.99,
            imageRes = R.drawable.product5,
            category = ProductCategory.BAGS,
        ),
        Product(
            id = 6,
            title = "Canvas Tote Bag",
            description = "Spacious canvas tote with interior pockets. Great for shopping trips and daily errands.",
            price = 29.99,
            imageRes = R.drawable.product6,
            category = ProductCategory.BAGS,
        ),
        Product(
            id = 7,
            title = "Suede Ankle Boots",
            description = "Soft suede ankle boots with block heel. Comfortable and stylish for autumn walks.",
            price = 109.99,
            imageRes = R.drawable.product7,
            category = ProductCategory.FOOTWEAR,
        ),
        Product(
            id = 8,
            title = "White Leather Sneakers",
            description = "Clean white leather sneakers with cushioned sole. Versatile and easy to style.",
            price = 74.99,
            imageRes = R.drawable.product8,
            category = ProductCategory.FOOTWEAR,
        ),
        Product(
            id = 9,
            title = "Cashmere Wool Scarf",
            description = "Luxuriously soft cashmere blend scarf. Keeps you warm while looking effortlessly chic.",
            price = 44.99,
            imageRes = R.drawable.product9,
            category = ProductCategory.ACCESSORIES,
        ),
        Product(
            id = 10,
            title = "Oversized Sunglasses",
            description = "Bold oversized sunglasses with UV protection. Statement piece for sunny days.",
            price = 39.99,
            imageRes = R.drawable.product10,
            category = ProductCategory.ACCESSORIES,
        ),
        Product(
            id = 11,
            title = "Slim Fit Chinos",
            description = "Tailored slim-fit chinos in neutral tone. Versatile for casual and smart-casual looks.",
            price = 54.99,
            imageRes = R.drawable.product11,
            category = ProductCategory.CLOTHING,
        ),
        Product(
            id = 12,
            title = "Leather Belt",
            description = "Premium leather belt with brushed silver buckle. The finishing touch to any outfit.",
            price = 24.99,
            imageRes = R.drawable.product12,
            category = ProductCategory.ACCESSORIES,
        ),
    )

    fun observeById(id: Int): Flow<Product?> {
        val item = products.find { it.id == id }
        return flowOf(item)
    }

    fun getById(id: Int): Product? {
        return products.find { it.id == id }
    }

    fun observeAll(): Flow<List<Product>> {
        return flowOf(products)
    }
}
