/*
 * Copyright (C) 2020  Michele Welponer, mwelponer@gmail.com (Fondazione Bruno Kessler)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.
 * If not, see <https://www.gnu.org/licenses/> and file GPL3.txt
 */

package eu.it.welponer.testPuremvc.model.database;

import java.sql.Connection;
import java.sql.ResultSet;

/*
 * Singleton to connect to Heroku database
 */
public class HerokuDBConnector extends DBConnector {

    private static HerokuDBConnector instance;

    static{
        instance = new HerokuDBConnector();
    }

    public static HerokuDBConnector getInstance(){
        return instance;
    }

    @Override
    Connection getConnection() {
        return null;
    }

    @Override
    ResultSet executeQuery(String query) {
        return null;
    }

    @Override
    int updateQuery(String query) {
        return 0;
    }
}
