package shop.lighthappy.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import shop.lighthappy.app.data.dao.CartItemDao
import shop.lighthappy.app.data.dao.OrderDao
import shop.lighthappy.app.data.database.converter.Converters
import shop.lighthappy.app.data.entity.CartItemEntity
import shop.lighthappy.app.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class IKEHPDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}