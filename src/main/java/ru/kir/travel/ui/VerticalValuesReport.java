package ru.kir.travel.ui;

import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ComponentColumnBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import ru.kir.travel.model.Client;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

public class VerticalValuesReport {
    private Client client;

    public VerticalValuesReport(Client client) {
        this.client = client;
        build();
    }

    private void build() {
        StyleBuilder nameStyle = stl.style().bold();
        StyleBuilder valueStyle = stl.style().setHorizontalAlignment(HorizontalAlignment.LEFT);

        FieldBuilder<String> itemField      = field("fio", type.stringType());
        FieldBuilder<String> quantityField  = field("passport",  type.stringType());
        FieldBuilder<String> unitPriceField = field("country", type.stringType());
        FieldBuilder<String> resortField = field("resort", type.stringType());
        FieldBuilder<String> hotel = field("hotel", type.stringType());
        FieldBuilder<String> arriveDate = field("dateArrive", type.stringType());
        FieldBuilder<Integer> date = field("date", type.integerType());
        FieldBuilder<Integer> orderDateField = field("cost", type.integerType());
        FieldBuilder<String> signatureField = field("signature", type.stringType());

        VerticalListBuilder nameList = cmp.verticalList(
                cmp.text("ФИО:").setStyle(nameStyle),
                cmp.text("Паспорт:").setStyle(nameStyle),
                cmp.text("Страна:").setStyle(nameStyle),
                cmp.text("Курорт:").setStyle(nameStyle),
                cmp.text("Отель:").setStyle(nameStyle),
                cmp.text("Дата заезда:").setStyle(nameStyle),
                cmp.text("Ночей:").setStyle(nameStyle),
                cmp.text("Цена:").setStyle(nameStyle),
                cmp.text("Подпись:").setStyle(nameStyle));
        VerticalListBuilder valueList = cmp.verticalList(
                cmp.text(itemField).setStyle(valueStyle),
                cmp.text(quantityField).setStyle(valueStyle),
                cmp.text(unitPriceField).setStyle(valueStyle),
                cmp.text(resortField).setStyle(valueStyle),
                cmp.text(hotel).setStyle(valueStyle),
                cmp.text(arriveDate).setStyle(valueStyle),
                cmp.text(date).setStyle(valueStyle),
                cmp.text(orderDateField).setStyle(valueStyle),
                cmp.text(signatureField).setStyle(valueStyle));

        ComponentColumnBuilder nameColumn = col.componentColumn("", nameList);
        ComponentColumnBuilder valueColumn = col.componentColumn("", valueList);

        try {
            report()
                    .setTemplate(Templates.reportTemplate)
                    .setPageFormat(PageType.A5)
                    .fields(itemField, quantityField, unitPriceField, orderDateField)
                    .columns(nameColumn, valueColumn)
                    .title(Templates.createTitleComponent("Заявка"))
                    .setDataSource(createDataSource())
                    .show(false);
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("fio", "passport", "country", "resort", "hotel", "dateArrive", "date", "cost", "signatureField");
        dataSource.add(client.getFio(), client.getPassport(), client.getTableTour().getCountry(),
                client.getTableTour().getResort(),
                client.getTableTour().getHotel(),
                client.getTableTour().getArrivalDate().toString(),
                client.getTableTour().getNights(),
                client.getTableTour().getCost(), "");
        return dataSource;
    }
}