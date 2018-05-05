Ext.onReady(function () {

    var Product = Ext.data.Record.create([
        {name: 'id'},
        {
            name: 'article',
            type: 'number'
        },
        {
            name: 'manufacturer',
            type: 'string'
        },
        {
            name: 'name',
            type: 'string'
        },
        {
            name: 'weight',
            type: 'number'
        },
        {
            name: 'price',
            type: 'number'
        }
    ]);

    var proxy = new Ext.data.HttpProxy({
        api: {
            read: 'product/view.action',
            create: 'product/create.action',
            update: 'product/update.action',
            destroy: 'product/delete.action'
        }
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: 'id',
        root: 'data',
        messageProperty: 'message'
    }, Product);


    var writer = new Ext.data.JsonWriter({
        encode: 'true',
        writeAllFields: true
    });

    var store = new Ext.data.Store({
        id: 'user',
        proxy: proxy,
        reader: reader,
        writer: writer,
        autoSave: false
    });

    store.load();


    Ext.data.DataProxy.addListener('exception', function (proxy, type, action, options, res) {
        console.log(res.message);
        Ext.Msg.show({
            title: 'ОШИБКА',
            msg: res.message,
            icon: Ext.MessageBox.ERROR,
            buttons: Ext.Msg.OK
        });
    });


    var cols = [
        {
            header: "Артикул",
            width: 70,
            sortable: true,
            dataIndex: 'article',
            editor: {
                xtype: 'textfield',
                allowBlank: false,
                regex: /^\d+$/,
                regexText: 'Артикул должен состоять из цифр',
                blankText: 'Поле артикула не может быть пустым'
            }

        },
        {
            header: "Производитель",
            width: 100,
            sortable: true,
            dataIndex: 'manufacturer',
            editor: {xtype: 'textfield', allowBlank: false, blankText: 'Поле производителя не может быть пустым'}
        },
        {
            header: "Название",
            width: 100,
            sortable: true,
            dataIndex: 'name',
            editor: {xtype: 'textfield', allowBlank: false, blankText: 'Поле названия не может быть пустым'}
        },
        {
            header: "Вес",
            width: 70,
            sortable: true,
            dataIndex: 'weight',
            editor: {
                xtype: 'textfield',
                allowBlank: false,
                regex: /^\d+(\.\d+)?$/,
                regexText: 'Вес должен состоять из цифр',
                blankText: 'Поле веса не может быть пустым'
            }
        },
        {
            header: "Цена",
            width: 70,
            sortable: true,
            dataIndex: 'price',
            editor: {
                xtype: 'textfield',
                allowBlank: false,
                regex: /^\d+(\.\d+)?$/,
                regexText: 'Цена должна состоять из цифр',
                blankText: 'Поле цены не может быть пустым'
            }
        }
    ];

    var editor = new Ext.ux.grid.RowEditor({
        saveText: 'Сохранить',
        cancelText: 'Отменить'
    });

    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: cols,
        viewConfig: {forcefit: true},
        plugins: [editor],
        title: 'Продукты',
        width: 450,
        height: 300,
        frame: true,
        tbar: [{
            iconCls: 'icon-add',
            text: 'Добавить продукт',
            handler: function () {
                var prod = new Product({
                    article: '',
                    manufacturer: '',
                    name: '',
                    weight: '',
                    price: ''
                });
                editor.stopEditing();
                store.insert(0, prod);
                grid.getView().refresh();
                grid.getSelectionModel().selectRow(0);
                editor.startEditing(0);
            }
        }, {
            iconCls: 'icon-delete',
            text: 'Удалить продукт',
            handler: function () {
                editor.stopEditing();
                var s = grid.getSelectionModel().getSelections();
                for (var i = 0, r; r = s[i]; i++) {
                    store.remove(r);
                }
            }
        }],
        bbar: [{
            iconCls: 'icon-save',
            text: 'Применить изменения',
            handler: function () {
                store.save();
            }
        }]
    });


    grid.render('panel')
});
