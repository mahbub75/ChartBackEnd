package Project.Files;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
class Chart {
    String type;
    String zoomType;

    public Chart() {
        this.type = "spline";
        this.zoomType = "xy";
    }
}

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
class Title {
    String text;

    public Title(String text) {
        this.text = text;
    }

}

@Getter
class YAxis {
    Title title;

    public YAxis(String title) {
        this.title = new Title(title);
    }
}

@Getter
class XAxis {
    boolean startOnTick;
    boolean endOnTick;
    boolean showLastLabel;
    Title title;

    public XAxis(String title) {
        this.title = new Title(title);
        this.startOnTick = true;
        this.showLastLabel = true;
        this.endOnTick = true;
    }
}

@Getter
class Legend {
    String layout;
    String align;
    String verticalAlign;
    int x;
    int y;
    boolean floating;
    String backgroundColor;
    int borderWidth;

    public Legend() {
        this.layout = "vertical";
        this.align = "center";
        this.verticalAlign = "top";
        this.x = 100;
        this.y = 70;
        this.floating = true;
        this.backgroundColor = "#FFFFFF";
        this.borderWidth = 1;
    }

}

@Getter
class Hover {
    boolean enabled;
    String lineColor;

    public Hover() {
        this.enabled = true;
        this.lineColor = "rgb(100,100,100)";
    }
}

@Getter
class States {
    Hover hover;

    public States() {
        this.hover = new Hover();
    }
}

@Getter
class Marker {
    double radius;
    States states;

    public Marker() {
        this.radius = .1;
        this.states = new States();
    }
}

@Getter
class Tooltip {
    String headerFormat;
    String pointFormat;

    public Tooltip() {
        this.headerFormat = "<b>{series.name}</b><br>";
        this.pointFormat = "{point.x} cm, {point.y} kg";
    }
}

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
class Scatter {
    Marker marker;
    Tooltip tooltip;

    public Scatter() {
        this.marker = new Marker();
        this.tooltip = new Tooltip();
    }

}

@Getter
class PlotOptions {
    Scatter scatter;

    public PlotOptions() {
        this.scatter = new Scatter();
    }

}

class Series {
    String name;
    String type = "line";
    String color = "rgba(223, 83, 83, .5)";
    ArrayList<int[]> data = new ArrayList<int[]>();

    public Series(String name) {
        this.setName(name);
        this.setData();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setData() {
        int[] d = new int[]{2, 3};
        int[] l = new int[]{3, 10};
        data.add(0, d);
        data.add(0, l);
    }

    public ArrayList<int[]> getData() {
        return data;
    }

    public void setType() {
        this.type = "line";
    }

    public String getType() {
        return type;
    }

}

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
public class ChartModel {
    Chart chart;
    Title title;
    Title subtitle;
    YAxis yAxis;
    XAxis xAxis;
    Legend legend;
    PlotOptions plotOptions;
    List<Series> series;

    public ChartModel(String title, String subTitle, String yAxisTitle, String xAxisTitle, List<Series> series) {
        this.chart = new Chart();
        this.title = new Title(title);
        this.subtitle = new Title(subTitle);
        this.yAxis = new YAxis(yAxisTitle);
        this.xAxis = new XAxis(xAxisTitle);
        this.legend = new Legend();
        this.plotOptions = new PlotOptions();
        this.series = series;
    }

}
