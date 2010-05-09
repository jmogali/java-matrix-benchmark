/*
 * Copyright (c) 2009-2010, Peter Abeles. All Rights Reserved.
 *
 * This file is part of JMatrixBenchmark.
 *
 * JMatrixBenchmark is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * JMatrixBenchmark is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JMatrixBenchmark.  If not, see <http://www.gnu.org/licenses/>.
 */

package jmbench.tools.runtime.generator;

import jmbench.tools.OutputError;
import jmbench.tools.runtime.InputOutputGenerator;
import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;
import org.ejml.ops.RandomMatrices;

import java.util.Random;


/**
 * @author Peter Abeles
 */
public class ScaleGenerator implements InputOutputGenerator {

    public static double SCALE = 2.5;

    DenseMatrix64F C;


    @Override
    public DenseMatrix64F[] createRandomInputs(Random rand, int matrixSize, boolean checkResults) {
        DenseMatrix64F A = RandomMatrices.createRandom(matrixSize,matrixSize,-1,1,rand);

        if( checkResults ) {
            C = new DenseMatrix64F(matrixSize,matrixSize);

            CommonOps.scale(SCALE,A,C);
        }

        return new DenseMatrix64F[]{A};
    }
    
    @Override
    public OutputError checkResults(DenseMatrix64F[] output, double tol) {
        return ResultsChecking.checkResult(output[0],C,tol);
    }

    @Override
    public int numOutputs() {
        return 1;
    }

    @Override
    public long getRequiredMemory( int matrixSize ) {
        return 8L*matrixSize*matrixSize*4L;
    }
}