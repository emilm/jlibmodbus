package com.invertor.modbus.msg.request;

import com.invertor.modbus.data.DataHolder;
import com.invertor.modbus.exception.ModbusNumberException;
import com.invertor.modbus.exception.ModbusProtocolException;
import com.invertor.modbus.msg.base.AbstractDataRequest;
import com.invertor.modbus.msg.base.ModbusResponse;
import com.invertor.modbus.msg.response.ReadFifoQueueResponse;
import com.invertor.modbus.net.stream.base.ModbusInputStream;
import com.invertor.modbus.net.stream.base.ModbusOutputStream;
import com.invertor.modbus.utils.ModbusFunctionCode;

import java.io.IOException;

/**
 * Copyright (c) 2015-2016 JSC Invertor
 * [http://www.sbp-invertor.ru]
 * <p/>
 * This file is part of JLibModbus.
 * <p/>
 * JLibModbus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p/>
 * Authors: Vladislav Y. Kochedykov, software engineer.
 * email: vladislav.kochedykov@gmail.com
 */
public class ReadFifoQueueRequest extends AbstractDataRequest {

    public ReadFifoQueueRequest(int serverAddress) throws ModbusNumberException {
        super(serverAddress);
    }

    public ReadFifoQueueRequest(int serverAddress, int startAddress) throws ModbusNumberException {
        super(serverAddress, startAddress);
    }

    @Override
    protected void writeData(ModbusOutputStream fifo) throws IOException {
        //no op
    }

    @Override
    protected void readData(ModbusInputStream fifo) throws IOException, ModbusNumberException {
        //no op
    }

    @Override
    protected int dataSize() {
        return 0;
    }

    @Override
    public ModbusResponse getResponse(DataHolder dataHolder) throws ModbusNumberException {
        ReadFifoQueueResponse response = new ReadFifoQueueResponse(getServerAddress());
        try {
            int[] r = dataHolder.readFifoQueue();
            response.setFifoValueRegister(r);
        } catch (ModbusProtocolException e) {
            response.setException();
            response.setModbusExceptionCode(e.getException().getValue());
        }
        return response;
    }

    @Override
    public boolean validateResponseImpl(ModbusResponse response) {
        return true;
    }

    @Override
    public int getFunction() {
        return ModbusFunctionCode.READ_FIFO_QUEUE.toInt();
    }
}