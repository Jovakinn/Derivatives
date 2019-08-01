package com.core;

import java.util.function.DoubleFunction;
import java.util.logging.Logger;

public class Derivatives {

    // limit
    private static final Double DX = 0.0001;
    private static Logger logger = Logger.getLogger(Derivatives.class.getName());

    private static DoubleFunction<Double> derive(DoubleFunction<Double> f){
        return (x) -> (f.apply(x + DX) - f.apply(DX)) / DX;
    }

    public static void main( String[] args ) {
        // f(x) = x^3
        DoubleFunction<Double> cube = (x) -> x * x * x;

        // f'(x) = 3 * x^2
        DoubleFunction<Double> cubeDeriv = derive(cube);

        // f(x) = sin(x), f'(x) = cos(x)
        DoubleFunction<Double> sinDeriv = derive(Math::sin);

        // checks

        // f'(2π) = cos(2π) = 1.0
        assert Math.round(sinDeriv.apply(Math.PI * 2)) == 1.0;
        // f'(2) = 3 * 2^2 = 12
        assert Math.round(cubeDeriv.apply(2)) == 12.0;
        // f'(3) = 3 * 3^2 = 27
        assert Math.round(cubeDeriv.apply(3)) == 27.0;
        // f'(4) = 3 * 4^2 = 48
        assert Math.round(cubeDeriv.apply(4)) == 48.0;

        // output

        logger.info(String.valueOf(Math.round(sinDeriv.apply(5 * Math.PI))));
    }
}
