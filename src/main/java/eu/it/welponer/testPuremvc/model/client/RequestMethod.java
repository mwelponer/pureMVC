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

package eu.it.welponer.testPuremvc.model.client;

import lombok.Getter;

public enum RequestMethod {
    GET(0),
    HEAD(1),
    POST(2),
    PUT(3),
    PATCH(4),
    DELETE(5),
    OPTIONS(6),
    TRACE(7);

    @Getter
    public final int index;

    RequestMethod(int index){
        this.index = index;
    }

    public static RequestMethod parse(int index){
        if(index == 0)
            return GET;
        if(index == 1)
            return HEAD;
        if(index == 2)
            return POST;
        if(index == 3)
            return PUT;
        if(index == 4)
            return PATCH;
        if(index == 5)
            return DELETE;
        if(index == 6)
            return OPTIONS;
        if(index == 7)
            return TRACE;

        return POST;
    }
}
