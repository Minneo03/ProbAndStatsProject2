data = csvread('SalterOctave.csv')
x = data(:,1)
y = data(:,2)

ys = y;
windowValue = 3; % Window Value
numTimes = 3; % number of times to smooth
tmp = 0;
count = 0;

for h = 1:numTimes %This entire for loop is just a copy of what I did in Java, but in Octave
  for i=1:51
    for j = i-windowValue:i+windowValue
      if (j > 0 && j < 52)
        tmp = tmp + ys(j)
        count = count  + 1
      endif
    endfor
    ys(i) = round(tmp/count); % Average Calculation
    tmp = 0;
    count = 0;
  end
end

plot(x, ys)
grid on
title('Smoother')
set(gca, 'fontsize', 24)
xlabel('X')
ylabel('Y')

z = reshape ([x;ys], 51, 2)
csvwrite('SmootherOctave.csv', z)
