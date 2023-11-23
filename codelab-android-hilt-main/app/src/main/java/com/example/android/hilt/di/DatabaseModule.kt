package com.example.android.hilt.di

import android.content.Context
import androidx.room.Room
import com.example.android.hilt.data.AppDatabase
import com.example.android.hilt.data.LogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * A Hilt module is a class annotated with @Module and @InstallIn.
 * @Module tells Hilt that this is a module and @InstallIn tells Hilt the containers
 * where the bindings are available by specifying a Hilt component.
 * You can think of a Hilt component as a container.
 *
 * Since LoggerLocalDataSource is scoped to the application container,
 * the LogDao binding needs to be available in the application container.
 * We specify that requirement using the @InstallIn annotation by passing in the
 * class of the Hilt component associated with it (i.e. SingletonComponent:class)
 */

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    /**
     * We can annotate a function with @Provides in Hilt modules to tell Hilt how to provide types that cannot be constructor injected.
     * The function body of a function that is annotated with @Provides
     * will be executed every time Hilt needs to provide an instance of that type.
     *
     * The return type of the @Provides-annotated function tells Hilt the binding type,
     * the type that the function provides instances of.
     * The function parameters are the dependencies of that type.
     */
    @Provides
    fun provideLogDao(database: AppDatabase): LogDao {
        return database.logDao()
    }

    /**
     * Since we always want Hilt to provide the same database instance,
     * we annotate the @Provides provideDatabase() method with @Singleton.
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "logging.db"
        ).build()
    }
}