package org.example.StructualPatterns.DecoratorPattern.TextExample;

// Text interface
interface Text {
    String getContent();
}

// Concrete text implementation
class PlainText implements Text {
    private String content;

    public PlainText(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}

// Decorator abstract class
abstract class TextDecorator implements Text {
    private Text text;

    public TextDecorator(Text text) {
        this.text = text;
    }

    @Override
    public String getContent() {
        return text.getContent();
    }
}

// Concrete decorators
class BoldText extends TextDecorator {
    public BoldText(Text text) {
        super(text);
    }

    @Override
    public String getContent() {
        return "<b>" + super.getContent() + "</b>";
    }
}

class ItalicText extends TextDecorator {
    public ItalicText(Text text) {
        super(text);
    }

    @Override
    public String getContent() {
        return "<i>" + super.getContent() + "</i>";
    }
}

class UnderlineText extends TextDecorator {
    public UnderlineText(Text text) {
        super(text);
    }

    @Override
    public String getContent() {
        return "<u>" + super.getContent() + "</u>";
    }
}

class ColoredText extends TextDecorator {
    private String color;

    public ColoredText(Text text, String color) {
        super(text);
        this.color = color;
    }

    @Override
    public String getContent() {
        return "<font color='" + color + "'>" + super.getContent() + "</font>";
    }
}

// Example usage
public class TextExampleDemo {
    public static void main(String[] args) {
        Text plainText = new PlainText("Hello, Decorator Pattern!");

        Text boldItalicText = new BoldText(new ItalicText(plainText));
        System.out.println(boldItalicText.getContent());

        Text coloredUnderlineText = new ColoredText(new UnderlineText(plainText), "blue");
        System.out.println(coloredUnderlineText.getContent());
    }
}

