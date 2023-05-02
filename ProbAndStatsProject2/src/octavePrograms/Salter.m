data = csvread('PlotterOctave.csv')
x = data(:,1)
y = data(:,2)

for i=1:51
  y(i) =  y(i) + randi([-50 50])
end

plot(x, y)
grid on
title('Salter')
set(gca, 'fontsize', 24)
xlabel('X')
ylabel('Y')

z = reshape ([x;y], 51, 2)
csvwrite('SalterOctave.csv', z)
