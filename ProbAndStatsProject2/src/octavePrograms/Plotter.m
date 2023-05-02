x = [-25:1:25];
y = x.^2 + 2 * x + 1;
plot(x,y)
grid on
title('Plotter')
set(gca, 'fontsize', 24)
xlabel('X')
ylabel('Y')
csvwrite('PlotterOctave.csv', [x;y]')

