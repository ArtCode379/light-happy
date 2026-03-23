package shop.lighthappy.app.data.model

import androidx.annotation.StringRes
import shop.lighthappy.app.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    CLOTHING(R.string.category_clothing),
    ACCESSORIES(R.string.category_accessories),
    FOOTWEAR(R.string.category_footwear),
    BAGS(R.string.category_bags),
}
