package top.kaluna.pipelineMonitor.drawCurve;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

/**
 * @author Yuery
 * @date 2022/3/9/0009 - 21:13
 * 从离散曲率到连续曲率的插值
 * 曲率插值大概就是用这样的方法得到连续的曲率值
 */
public class PolynomialSpline {
    public static void main(String[] main) {
        double [] xDouble = new double [] {24.0, 35.0, 67.0, 78.0,79.0};
        double [] yDouble = new double [] {13.0, 45.0, 8.0, 45.0, 23.0};

        SplineInterpolator si = new SplineInterpolator();
        PolynomialSplineFunction psf = si.interpolate(xDouble, yDouble);

        for (PolynomialFunction pf : psf.getPolynomials()) {
            System.out.println(pf.polynomialDerivative());
        }
    }
}
