package Project.Files;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import org.json.JSONObject;

import java.util.ArrayList;
import javax.xml.crypto.Data;
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
    String type = "spline";
    String color;
    ArrayList<Double[]> data = new ArrayList<Double[]>();

    public Series() {

    }

    public Series(String name, String color, ArrayList<Double[]> data) {
        this.setName(name);
        this.setColor(color);
        this.setData(data);

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

    public void setData(ArrayList<Double[]> data) {
        this.data = data;
    }

    public ArrayList<Double[]> getData() {
        return data;
    }

    public void setType(String type) {
        this.type = type;
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
    private YAxis yAxis;
    private XAxis xAxis;
    Legend legend;
    PlotOptions plotOptions;
    List<Series> series;

    public ChartModel(String title, String subTitle, String yAxisTitle, String xAxisTitle, List<Series> series) {
        this.chart = new Chart();
        this.title = new Title(title);
        this.subtitle = new Title(subTitle);
        this.setXAxis(xAxisTitle);
        this.setYAxis(yAxisTitle);
        this.legend = new Legend();
        this.plotOptions = new PlotOptions();
        this.series = series;
    }

    public void setXAxis(String xAxisTitle) {
        this.xAxis = new XAxis(xAxisTitle);
    }

    @JsonProperty("xAxis")
    public XAxis getXAxis() {
        return xAxis;
    }

    public void setYAxis(String yAxisTitle) {
        this.yAxis = new YAxis(yAxisTitle);
    }

    @JsonProperty("yAxis")
    public YAxis getYAxis() {
        return yAxis;
    }


}
