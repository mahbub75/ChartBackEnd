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
   // String type;
    String zoomType;

    public Chart() {
//       this.type = "spline";
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
    ArrayList<Double[]> data = new ArrayList<Double[]>();
    String dashStyle = "none";
    //  String type = "spline";
    String color;
    int offSet = 0;
  int  voltDiv = 1;
   Boolean sign = false;
   Boolean isChecked=true;

    public Series() {

    }

    public Series(String name, String color, ArrayList<Double[]> data) {
        this.name = name;
        this.color = color;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double[]> getData() {
        return data;
    }

    public String getDashStyle() { return dashStyle; }

    public String getColor() {
        return this.color;
    }

    public int getOffSet() {
        return offSet;
    }

    public int getVoltDiv() { return voltDiv; }

    public Boolean getIsChecked() { return isChecked; }

    public Boolean getSign() { return sign; }


}

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
class ChartContent {
    Chart chart;
    Title title;
    Title subtitle;
    private YAxis yAxis;
    private XAxis xAxis;
    Legend legend;
    PlotOptions plotOptions;
    List<Series> series;

    public ChartContent(String title, String yAxisTitle, String xAxisTitle, List<Series> series) {
        this.chart = new Chart();
        this.title = new Title(title);
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

@Getter
class ChartModel {
   ChartContent content;
    String description;

    public ChartModel( ChartContent content,String description) {
        this.setContent(content);
        this.setDescription(description);
    }

    public void setContent(ChartContent content) {
        this.content = content;
    }

    public void setDescription(String description) {
        this.description=description;
    }

}