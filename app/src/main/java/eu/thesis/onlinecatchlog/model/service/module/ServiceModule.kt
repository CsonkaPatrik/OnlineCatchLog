package eu.thesis.onlinecatchlog.model.service.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.thesis.onlinecatchlog.model.service.AccountService
import eu.thesis.onlinecatchlog.model.service.impl.AccountServiceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

}
