function PlotterProgram()
    % Parameters
    a = 1; 
    b = 0; 
    c = 0; 
    x_start = 0; 
    x_end = 50; 
    step = 0.5; 
    salt_range = 5; 
    window_size = 4; 
    
    % Generate data
    [x, y] = generateRootFunctionData(x_start, x_end, step, a, b, c);
    disp('Original data:');
    disp(table(x', y', 'VariableNames', {'x', 'y'}));
    
    % Plot original data
    figure;
    plot(x, y, 'b', 'LineWidth', 1.5);
    title('Original Data');
    xlabel('x');
    ylabel('y');
    grid on;
    
    % Salt the data
    salted_y = saltData(y, salt_range);
    disp('Salted data:');
    disp(table(x', salted_y', 'VariableNames', {'x', 'y'}));
    
    % Plot salted data
    figure;
    plot(x, salted_y, 'r', 'LineWidth', 1.5);
    title('Salted Data');
    xlabel('x');
    ylabel('y');
    grid on;
    
    % Smooth the data
    smoothed_y = smoothData(salted_y, window_size);
    disp('Smoothed data:');
    disp(table(x', smoothed_y', 'VariableNames', {'x', 'y'}));
    
    % Plot smoothed data
    figure;
    plot(x, smoothed_y, 'g', 'LineWidth', 1.5);
    title('Smoothed Data');
    xlabel('x');
    ylabel('y');
    grid on;
end

function [x, y] = generateRootFunctionData(start, endVal, increment, a, b, c)
    x = start:increment:endVal;
    y = a * sqrt(abs(x) + b) + c;
end

function salted_y = saltData(y, range)
    noise = (rand(size(y)) * 2 - 1) * range;
    salted_y = y + noise;
end

function smoothed_y = smoothData(y, window_size)
    n = length(y);
    smoothed_y = zeros(size(y));
    
    for i = 1:n
        start_idx = max(1, i - window_size);
        end_idx = min(n, i + window_size);
        smoothed_y(i) = mean(y(start_idx:end_idx));
    end
end