import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AStarPathingStrategy implements PathingStrategy
{
    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors)
    {
        PriorityQueue<Node> openList = new PriorityQueue<Node>((node1, node2) -> (int) (node1.getfValue() - node2.getfValue()));
        HashMap<Point, Node> openListLookUp = new HashMap<>();
        HashSet<Point> closedList = new HashSet<Point>();
        Node current = new Node(start, end, null);
        List<Point> path = new LinkedList<Point>();

        openList.add(current);
        openListLookUp.put(current.getPos(), current);

        while (!openList.isEmpty() && !current.getPos().equals(end) && !withinReach.test(current.getPos(), end)) {
            List<Point> validAdjacents = potentialNeighbors.apply(current.getPos()).filter(canPassThrough).filter(p -> !closedList.contains(p)).collect(Collectors.toList());
            for (Point p: validAdjacents) {
                Node validNeighbor = new Node(p, end, current);
                validNeighbor.generategValue();
                if (openListLookUp.containsKey(p)) {
                    Node predecessor = openListLookUp.get(p);
                    if (validNeighbor.getgValue() < predecessor.getgValue()) {
                        predecessor.setPrior(current);
                        predecessor.generategValue();
                        openList.remove(predecessor);
                        openListLookUp.remove(p);
                        validNeighbor = predecessor;
                    }
                    else
                        continue;
                }
                validNeighbor.generatehValue();
                validNeighbor.generatefValue();
                openList.add(validNeighbor);
                openListLookUp.put(p, validNeighbor);
            }
            openList.remove(current);
            closedList.add(current.getPos());
            current = openList.peek();
        }
        while (current != null && current.getPrior() != null) {
            ((LinkedList<Point>) path).addFirst(current.getPos());
            current = current.getPrior();
        }
        return path;
    }
}
