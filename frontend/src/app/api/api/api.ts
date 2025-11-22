export * from './adminController.service';
import { AdminControllerService } from './adminController.service';
export * from './authController.service';
import { AuthControllerService } from './authController.service';
export * from './homeController.service';
import { HomeControllerService } from './homeController.service';
export const APIS = [AdminControllerService, AuthControllerService, HomeControllerService];
