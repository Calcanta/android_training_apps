package com.example.android.hilt.di

import com.example.android.hilt.navigator.AppNavigator
import com.example.android.hilt.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Modules are used to add bindings to Hilt, or in other words,
 * to tell Hilt how to provide instances of different types.\
 *
 * A Hilt module is a class annotated with @Module and @InstallIn.
 * @Module tells Hilt that this is a module and
 * @InstallIn tells Hilt the containers where the bindings are available by specifying a Hilt component.
 *
 * Our new navigation information (i.e. AppNavigator) needs information specific
 * to the activity becauseAppNavigatorImpl has an Activity as a dependency.
 * Therefore, it must be installed in the Activity container
 */

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {

    /**
     * Because AppNavigator is an interface, we cannot use constructor injection.
     *
     * To tell Hilt what implementation to use for an interface,
     * you can use the @Binds annotation on a function inside a Hilt module.
     *
     * @Binds must annotate an abstract function (since it's abstract,
     * it doesn't contain any code and the class needs to be abstract too).
     *
     * The return type of the abstract function is the interface we want to provide an implementation
     * for (i.e. AppNavigator).
     * The implementation is specified by adding a unique parameter with the interface implementation type
     * (i.e. AppNavigatorImpl).
     */
    @Binds
    abstract fun bindNavigator(impl: AppNavigatorImpl): AppNavigator
}