import { createPool } from "mysql2/promise";
import { HOST_DB, PASSWORD_DB, USER_DB, PORT_DB, DATABASE } from "../config/config.js";

/*
export const db_pool_connection = createPool(
    {
        host: HOST_DB,
        user: USER_DB,
        password: PASSWORD_DB,
        database: DATABASE,
        port: PORT_DB
    }
);
*/

export const db_pool_connection = createPool(
    {
        host: 'localhost',
        user: 'root',
        password: 'admin89',
        database: 'phone_care',
        port: 3306
    }
);