classdef Plotter
    
    methods (Static)
        function [xValues, yValues] = generateData(start, stop, step, a, b, c)
            xValues = start:step:stop;
            yValues = arrayfun(@(x) a * sqrt(abs(x) + b) + c, xValues);
        end

        function saltedValues = saltData(yValues, range)
            noise = (rand(size(yValues)) * 2 - 1) * range;
            saltedValues = yValues + noise;
        end

        function smoothedValues = smoothData(yValues, windowSize)
            n = length(yValues);
            smoothedValues = zeros(size(yValues));

            for i = 1:n
                left = max(1, i - windowSize);
                right = min(n, i + windowSize);
                smoothedValues(i) = mean(yValues(left:right));
            end
        end

        function plotData(xValues, yValues, titleText)
            figure;
            plot(xValues, yValues, 'LineWidth', 2);
            grid on;
            xlabel('X Values');
            ylabel('Y Values');
            title(titleText);
        end
    end
end