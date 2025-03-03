/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2023 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.model.impl.data;

import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.data.DBDValue;
import org.jkiss.utils.CommonUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * DBDValueError
 */
public final class DBDValueError implements DBDValue {

    private final Throwable error;

    public DBDValueError(Throwable error) {
        this.error = error;
    }

    @Override
    public Object getRawValue() {
        return error;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void release() {

    }

    public String getErrorTitle() {
        if (error instanceof DBException) {
            return error.getMessage();
        }
        return error.getClass().getSimpleName() + ": " + CommonUtils.notEmpty(error.getMessage());
    }

    @Override
    public String toString() {
        StringWriter buf = new StringWriter();
        error.printStackTrace(new PrintWriter(buf, true));
        return buf.toString();
    }

}
