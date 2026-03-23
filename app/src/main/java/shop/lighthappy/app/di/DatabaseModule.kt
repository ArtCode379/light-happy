package shop.lighthappy.app.di

import androidx.room.Room
import shop.lighthappy.app.data.database.IKEHPDatabase
import org.koin.dsl.module

private const val DB_NAME = "ikehp_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = IKEHPDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<IKEHPDatabase>().cartItemDao() }

    single { get<IKEHPDatabase>().orderDao() }
}