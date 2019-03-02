import java.lang.*;

public class QueueSimulator {
    public enum Event {ARRIVAL, DEPARTURE}

    ;
    private double currTime;
    private double arrivalRate;
    private double serviceTime;
    private double timeForNextArrival;
    private double timeForNextDeparture;
    private double totalSimTime;
    LinkedListQueue<Data> buffer = new LinkedListQueue<Data>();
    LinkedListQueue<Data> eventQueue = new LinkedListQueue<Data>();
    private Event e;

    public double getRandTime(double arrivalRate) {
        double num, time1, max = 1, min = 0, randNUM;
        randNUM = Math.random();
        time1 = (-1 / arrivalRate) * (Math.log(1 - randNUM));
        //System.out.println(time1);
        return time1;
    }

    public QueueSimulator(double aR, double servT, double simT) {
        arrivalRate = aR;
        serviceTime = servT;
        totalSimTime = simT;
        currTime = 0;
    }

    public double calcAverageWaitingTime() {
        double waitTime = 0;
        int size = 0;
        while(!eventQueue.isEmpty()){
            Data temp = eventQueue.dequeue();
            waitTime += temp.getDepartureTime() - temp.getArrivalTime();
            size++;
        }

        return waitTime / size;
    }

    public double runSimulation() {
        timeForNextArrival = getRandTime(arrivalRate);
        timeForNextDeparture = serviceTime + timeForNextArrival;

        while(currTime < totalSimTime){
            if(timeForNextArrival < timeForNextDeparture || buffer.isEmpty()){
                currTime += timeForNextArrival;
                Data temp = new Data();
                temp.setArrivalTime(currTime);
                buffer.enqueue(temp);

                timeForNextDeparture = timeForNextDeparture - timeForNextArrival;
                timeForNextArrival = getRandTime(arrivalRate);
            }
            else{

                currTime += timeForNextDeparture;
                Data temp = buffer.dequeue();
                temp.setDepartureTime(currTime);
                eventQueue.enqueue(temp);

                timeForNextArrival = timeForNextArrival - timeForNextDeparture;
                if(buffer.isEmpty()) {
                    timeForNextDeparture = timeForNextArrival + serviceTime;
                }
                else{
                    timeForNextDeparture = serviceTime;
                }
            }
        }

        return calcAverageWaitingTime();
    }
}






