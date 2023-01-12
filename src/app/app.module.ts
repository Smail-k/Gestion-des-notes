
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LocationStrategy, PathLocationStrategy } from '@angular/common';
import { AppRoutes } from './app.routing';
import { AppComponent } from './app.component';

import { FlexLayoutModule } from '@angular/flex-layout';
import { FullComponent } from './layouts/full/full.component';
import { AppSidebarComponent } from './layouts/full/sidebar/sidebar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DemoMaterialModule } from './demo-material-module';

import { SharedModule } from './shared/shared.module';
import { SpinnerComponent } from './shared/spinner.component';
import { LoginComponent } from './login/login.component';
import { AuthInterceptor } from './guards/auth.interceptor';
import { IsSignedGuard } from './guards/is-signed.guard';

@NgModule({
  declarations: [
    AppComponent,
    FullComponent,
    SpinnerComponent,
    AppSidebarComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    DemoMaterialModule,
    FlexLayoutModule,
    HttpClientModule,
    FormsModule,
    SharedModule,
    RouterModule.forRoot(AppRoutes)
  ],
  providers: [

    IsSignedGuard,
    {   
    provide: LocationStrategy,
    useClass: PathLocationStrategy,
    },
  
  
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}