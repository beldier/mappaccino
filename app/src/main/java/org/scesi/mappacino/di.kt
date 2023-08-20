package org.scesi.mappacino

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.ksp.generated.module
import org.scesi.mappacino.data.DataModule
import org.scesi.mappacino.usescases.UseCasesModule

fun Application.initDI() {
    startKoin {
        androidLogger(Level.ERROR)
        androidContext(this@initDI)
        modules(AppModule().module,DataModule().module, UseCasesModule().module)
    }
}

@Module
@ComponentScan
class AppModule{

}