import { db_pool_connection } from "../database/db.js";
import { response_created, response_not_found, response_sucess, response_bad_request, response_error } from "../responses/responses.js";



export const getUsuarios = async (req, res) => {

    try {
        
        const [rows] = await db_pool_connection.query("SELECT * FROM TB_USER WHERE TIPO_USER <> 'administrador'");
        console.log(rows);
        if (rows.length <= 0) {
            return res.status(404).json(response_not_found("No se encontraron registros de clientes"));
        }
        return res.status(200).json(response_sucess(rows, "Consulta Exitosa"));

    }
    catch (error) {
        res.status(500).json(response_error(500, "Error al obtener los datos de los clientes => " + error['sqlMessage']));
    }
}

export const validacionLogin = async (req, res) => {

    try {
        const { usuario, contrasena } = req.body;

        const [rows] = await db_pool_connection.query('SELECT * FROM TB_USER WHERE USUARIO = ? AND PASWORD = ?',
            [usuario, contrasena]
        );

        if (rows.length <= 0) {
            return res.status(404).json(response_not_found("No se encontraron registros"));
        }

        const tipoUsuario = rows[0].TIPO_USER;
        const idUsuario = rows[0].ID_USER;
        const requerimientoUsuario = rows[0].REQUERIMIENTO;
        const accesoriosUsuario = rows[0].ACCESORIOS;
        const estadoEquipoUsuario = rows[0].ESTADO_EQUIPO;
        const pagoUsuario = rows[0].PAGO;
        const nombreUsuario = rows[0].NOMBRE;
        const marcaTelUsuario = rows[0].MARCA_TELEFONO;
        const comentario = rows[0].COMENTARIO;



        return res.status(200).json({
            message: response_sucess(`Credenciales validas. Usted es : ${tipoUsuario} ${idUsuario}`),
            tipoUsuario: tipoUsuario,
            idUsuario: idUsuario,
            requerimientoUsuario: requerimientoUsuario,
            accesoriosUsuario: accesoriosUsuario,
            estadoEquipoUsuario: estadoEquipoUsuario,
            pagoUsuario: pagoUsuario,
            nombreUsuario: nombreUsuario,
            marcaTelUsuario: marcaTelUsuario,
            comentario: comentario
        });

    } catch (error) {
        res.status(500).json(response_error(500, "Error al validar credenciales => " + error['sqlMessage']));
    }

}

export const crearCliente = async (req, res) => {

    try {
        const { USUARIO, PASWORD, CEDULA, NOMBRE, CORREO, WHATSAPP, MARCA_TELEFONO } = req.body;
        const TIPO_USER = 'cliente';
        const REQUERIMIENTO = 'sin comentarios';
        const ACCESORIOS = 'sin comentarios';
        const ESTADO_EQUIPO = 'sin comentarios';
        const PAGO = 'sin comentarios';
        const COMENTARIO = 'sin comentarios';

        const [rows] = await db_pool_connection.query(
            "INSERT INTO TB_USER (USUARIO, PASWORD, TIPO_USER, CEDULA, NOMBRE, CORREO, WHATSAPP, MARCA_TELEFONO, REQUERIMIENTO, ACCESORIOS, ESTADO_EQUIPO, PAGO, COMENTARIO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)",
            [USUARIO, PASWORD, TIPO_USER, CEDULA, NOMBRE, CORREO, WHATSAPP, MARCA_TELEFONO, REQUERIMIENTO, ACCESORIOS, ESTADO_EQUIPO, PAGO, COMENTARIO]
        );
        res.status(201).json(response_created(rows.insertId, "Cliente creado con Exito"));
    }
    catch (error) {
        res.status(500).json(response_error(500, "Error al Insertar los datos del cliente => " + error['sqlMessage']));
    }

}

export const actualizarCliente = async (req, res) => {

    try {

        const id = req.params.id;


        const { USUARIO, PASWORD, CEDULA, NOMBRE, CORREO, WHATSAPP, MARCA_TELEFONO } = req.body;

        if (id === null)
            return res.status(400).json(response_bad_request("Parámetro Id no Válido"))
        const [rows] = await db_pool_connection.query(
            "UPDATE TB_USER SET USUARIO = ?, PASWORD = ?, CEDULA = ?, NOMBRE = ?, CORREO = ?, WHATSAPP=?, MARCA_TELEFONO=? WHERE ID_USER = ?",
            [USUARIO, PASWORD, CEDULA, NOMBRE, CORREO, WHATSAPP, MARCA_TELEFONO, id]);
        console.log(rows);
        if (rows.length <= 0) {
            return res.status(404).json(response_not_found("No se encontraron registros de clientes"));
        }
        return res.status(200).json(response_sucess(rows, "Cliente Actualizado con Exito"));
    } catch (error) {
        res.status(500).json(response_error(500, "Error al Actualizar los datos del cliente => " + error['sqlMessage']));
    }
};

export const eliminarCliente = async (req, res) => {

    try {
        const id = req.params.id;
        if (id === null)
            return res.status(400).json(response_bad_request("Parámetro Id no Válido"))

        const [rows] = await db_pool_connection.query('DELETE FROM TB_USER where ID_USER=?', [id]);
        console.log(rows);

        if (rows.length <= 0) {
            return res.status(404).json(response_not_found("No se encontraron registros de cliente"));
        }
        return res.status(200).json(response_sucess(rows, "Cliente eliminado con Exito"));
    } catch (error) {
        res.status(500).json(response_error(500, "Error al Eliminar los datos del cliente => " + error['sqlMessage']));
    }
}

export const actualizarMantenimiento = async (req, res) => {

    try {

        const id = req.params.id;

        const { REQUERIMIENTO, ACCESORIOS, ESTADO_EQUIPO, PAGO, COMENTARIO } = req.body;

        if (id === null)
            return res.status(400).json(response_bad_request("Parámetro Id no Válido"))
        const [rows] = await db_pool_connection.query(
            "UPDATE TB_USER SET REQUERIMIENTO = ?, ACCESORIOS = ?, ESTADO_EQUIPO = ?, PAGO = ? WHERE ID_USER = ?",
            [REQUERIMIENTO, ACCESORIOS, ESTADO_EQUIPO, PAGO, id]);
        console.log(rows);
        if (rows.length <= 0) {
            return res.status(404).json(response_not_found("No se encontraron registros de cliente"));
        }
        return res.status(200).json(response_sucess(rows, "MAntenimiento Actualizado con Exito"));
    } catch (error) {
        res.status(500).json(response_error(500, "Error al Actualizar los datos del mantenimiento => " + error['sqlMessage']));
    }
};

export const actualizarComentario = async (req, res) => {

    try {

        const id = req.params.id;


        const { COMENTARIO } = req.body;

        if (id === null)
            return res.status(400).json(response_bad_request("Parámetro Id no Válido"))
        const [rows] = await db_pool_connection.query(
            "UPDATE TB_USER SET COMENTARIO = ? WHERE ID_USER = ?",
            [COMENTARIO, id]);
        console.log(rows);
        if (rows.length <= 0) {
            return res.status(404).json(response_not_found("No se encontraron registros de clientes"));
        }
        return res.status(200).json(response_sucess(rows, "Comentario registrado con Exito"));
    } catch (error) {
        res.status(500).json(response_error(500, "Error al Actualizar el Comentario => " + error['sqlMessage']));
    }
};

