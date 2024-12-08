start = 0; 
stop = 50; 
step = 0.5; 
a = 2; 
b = 1; 
c = 0;

% Generate the data
[xValues, yValues] = Plotter.generateData(start, stop, step, a, b, c);
Plotter.plotData(xValues, yValues, 'Original Data');

% Salt the data
saltRange = 2;
saltedValues = Plotter.saltData(yValues, saltRange);
Plotter.plotData(xValues, saltedValues, 'Salted Data');

% Smooth the data
windowSize = 12;
smoothedValues = Plotter.smoothData(saltedValues, windowSize);
Plotter.plotData(xValues, smoothedValues, 'Smoothed Data');
