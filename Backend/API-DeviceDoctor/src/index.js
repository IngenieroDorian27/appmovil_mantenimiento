import express from "express";
import { PORT, config_core } from "./config/config.js";
import routes_proyecto from "./routes/proyecto_routes.js";
import cors from "cors";


const app = express();

//definir politicas de acceso al API

app.use(cors(config_core.application.cors.server));

//Response sean en formato Jason
app.use(express.json());

//Rutas que escuchan y estan establecidas
app.use(routes_proyecto);

//Respuetas para accesos a rutan no validas
app.use((req, res, next) => {
    res.status(404).json({message: "Ruta no valida"});
});


app.listen(PORT, () => { console.log("Servidor escuchado por el puerto 3200", PORT); })

