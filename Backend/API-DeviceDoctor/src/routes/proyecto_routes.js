import { Router } from "express";
import { getUsuarios, validacionLogin, crearCliente, actualizarCliente, eliminarCliente, actualizarMantenimiento, actualizarComentario} from "../controllers/proyecto_controllers.js"

const routes_proyecto = new Router();

routes_proyecto.get('/usuarios', getUsuarios);
routes_proyecto.post('/validacionlogin', validacionLogin);
routes_proyecto.post('/crearCliente', crearCliente);
routes_proyecto.put('/actualizarCliente/:id', actualizarCliente);
routes_proyecto.put('/actualizarMantenimiento/:id', actualizarMantenimiento);
routes_proyecto.put('/actualizarComentario/:id', actualizarComentario);
routes_proyecto.delete('/eliminarCliente/:id', eliminarCliente);

export default routes_proyecto;