export * from './adminController.service';
import { AdminControllerService } from './adminController.service';
export * from './homeController.service';
import { HomeControllerService } from './homeController.service';
export const APIS = [AdminControllerService, HomeControllerService];
