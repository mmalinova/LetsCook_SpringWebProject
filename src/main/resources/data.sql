INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`, `password`, `image_url`, `active`)
VALUES ('1', 'malinova29@gmail.com', 'Mihaela', 'Malinova', '3006aa2b1ef3c11a2de1ede20a6b3a17318186e24700bcc3028e746c7ced12e8992541d2a9639765', null, true),
       ('2', 'georgi@gmail.com', 'Georgi', 'Georgiev', '096253277e94af27413316ab81d3ad32fb00a4250db3883c5e9cc540718f6ff11446aac0eae0e0db', null, false),
       ('3', 'petko@gmail.com', 'Petko', 'Petkov', 'f1df40fe2eb2422ae329b068993f8458d78c9311e138930b7a4d9640fa6dd0e6ea1dab0681396b00', null, false);

INSERT INTO `role` (`id`, `role`)
VALUES ('1', 'ADMIN'),
       ('2', 'MODERATOR');

INSERT INTO `user_roles` (`user_id`, `role_id`)
VALUES ('1', '1'),
       ('1', '2'),
       ('2', '2');

INSERT INTO `category` (`id`, `category`)
VALUES ('1', 'ЗАКУСКА'),
       ('2', 'ОБЯД'),
       ('3', 'ВЕЧЕРЯ'),
       ('4', 'ДЕСЕРТ');

INSERT INTO `recipe` (`id`, `name`, `subcategory`, `vegetarian`, `portions`, `steps`, `hours`, `minutes`, `created_on`, `approved`, `ingredients`, `owner_id`, `category_id`)
VALUES ('1', 'БИСКВИТЕНА ТОРТА С КРЕМ БЕЙЛИС', 'ТОРТА', true, 20, 'Първата стъпка от рецептата за бисквитена торта с крем Бейлис е подготовката на пудинга. Спокойно може да замените пудинга ванилия с домашен крем Ванилия, но с готов пудинг става по-бързо и по-лесно. Пригответе пудинга според упътванията на опаковката, което става по следния начин: По-голяма част от прясното мляко, заедно със захарта сложете на котлона, за да заври и да се разтвори захарта, след което свалете от котлона. Смесете останалата около 1 чаена чаша студено прясно мляко с пудинга и бъркайте докато се разтвори. При непрекъснато бъркане с вилица или тел за разбиване изсипете млякото с пудинга към млякото със захарта и бъркайте. Върнете на слаб огън на котлона и бъркайте непрекъснато докато сместа заври, а след това още 3-4 минути докато се сгъсти. Свалете на котлона и оставете на страни, за да се охлади.
Междувременно в голяма купа разбийте с миксер маскарпонето и пудрата захара, добавете и предвиденото количество Бейлис и отново разбийте до получаване на хомогенна смес. С шпатула или дървена лъжица смесете напълно охладения пудинг и крема с Бейлис.
В правоъгълна форма наредете ред от бисквитите, покрийте обилно с крем, поръсвате с една-две лъжици мармалад. След това отново бисквити, крем, мармалад и така продължавате да редувате, докато свършат съставките, като завършвате с крем. Най-добре е да използвате мармалад от червени плодове, за да бъде по-ефектна тортата. Прибирате тортата в хладилника и я забравяте там поне за 3-4 часа, за да се напои добре. Може да изберете най-ефектният за вас начин за сервиране - на топки с помощта на лъжица за сладолед, или на парчета поръсени със ситно накълцани орехи или бадеми.', 0, 30, '2021/12/30', true, 'прясно мляко, маскарпоне, захар, бисквити Закуска, пудинг, пудра захар, бейлис, мармалад', 1, 4),
       ('2', 'МЛЕЧНА БАНИЦА', 'БАНИЦА', true, 12, 'Първата стъпка от рецептата за млечна баница е подготовката на корите. Кравето масло трябва да разтопено, може да разтопите маслото на водна баня, или пък в микровълновата. Намажете обилно тавата с краве масло, след което сложете една кора от корите за баница, намажете я с разтопено краве масло, поставете нова кора, намажете и нея, и продължете така, докато корите свършат, като последнат кора също намажете добре с масло. Когато поставяте корите ги разпределяйте равномерно, така че в цялата тава да има еднакъв пласт кори.
С остър нож направете няколко къси разреза по корите и печете в предварително загрята на 180 градуса фурна за около 25-30 минути, или докато корите придобият златист цвят.
Междувременно разбивате разбивате яйцата със захарта с помощта на тел за разбиване или миксер, след което добавяте и леко загрятото, но не горещо мляко и ванилията. Разбивате до пълно разтваряне на захарта. Върху изпечените кори изливате така приготвената смес и печете за още около 20 минути, докато баницата е напълно изпечена. Преди сервиране поръсете баницата с пудра захар.', 0, 30, '2021/12/30', true, 'точени кори, яйца, ванилии, пудра захар, прясно мляко, захар, краве масло', 1, 1),
       ('3', 'БАНАНОВА ВЕГАН ТОРТА С КОКОС', 'ТОРТА', true, 10, 'Първата стъпка от рецептата за сурова бананова веган торта с кокос е подготовката на бадемите за блата. Накисвате суровите бадеми за минимум 6-7 часа, а най-добре за една нощ. След като са престояли във водата ядките са готови, отцеждате ги и ги смилате с блендер или кухненски робот, заедно с почистените от костилките фурми. Смилате до получавате на гъста каша с парченца ядки. Във форма за сладкиш, най-добре с падащи стени поставяте хартия за печене и разпределяте равномерно сместа за блата.
Отново с помощта на блендер или кухненски робот смилате стафидите, добавяте бананите, меласата/меда, брашното от рожков и ванилията. Брашното от рожков се използва като заместител на какаото, така че спокойно може да го замените с неподсладено какао в същото количество.
След като получите хомогенна смес добавете, кокосовите стърготини и семената чия и разбъркайте добре с шпатула или дървена лъжица, така че да се разпределят равномерно в сместа. Полученият крем изсипете върху блата на равен пласт. Оставете суровата бананова торта в хладилника за два-три часа, за да стегне. Преди сервиране украсете тортата, като вариант за украса е да поръсите сладкиша с кокосови стърготини, както е украсен и сладкиша на снимките.', 0, 15, '2022/01/02', true, 'фурми, банан, мед, стафиди, ванилия, бадеми, какао, кокосови стърготини, чия', 1, 4),
       ('4', 'ЯЙЧЕНА САЛАТА С МАЙОНЕЗА И ПИЛЕ', 'САЛАТА', false, 6, 'Първата стъпка от рецептата за яйчена салата с майонеза и пиле е варенето на яйцата и пилето. Яйцата трябва да бъдат твърдо сварени за около 10 минути. Пилето също трябва да се свари добре, като за приготвяне на салатата може да ползвате всякакво пилешко месо - бяло месо, бутчета, или каквото имате под ръка.
След като яйцата са сварени ги обелете, а пилето нареже на ситно.
В купа сложете нарязаното пиле, добавете и ситно нарязаните яйца, както и ситно нарязания лук. Добавете лимоновия сок и 1.5 чаена лъжица сол, както и черния пипер и разбъркайте. След това добавете майонезата и горчицата и отново разбъркайте.', 0, 30, '2022/01/02', true, 'яйца, лук, горчица, черен пипер, пилешко месо, майонеза, лимонов сок', 1, 3),
       ('5', 'ТЕЛЕШКО ВАРЕНО', 'СУПА', false, 10, 'Първата стъпка от рецептата за телешко варено е подготовката на джолана. Измивате добре телешкия джолан и го слагате в голяма тенджера  със студена вода и го слагате на котлона да заври. След като водата заври се образува пяна, отстранявате пяната, прецеждате бульона, след което връщате супата на котлона. Добавяте оцета, около 1.5 чаена лъжица сол  и черен пипер и оставяте джолана да ври до пълно сваряване. По време на варенето следете водата да е над джолана и ако се наложи доливайте.
Докато джоланът ври, почистете картофите, морковите, лука и целината. Използвайте по-малки картофи и лук, оставете ги цели, а ако имате по-едри, то ги нарежете на големи парчета.Доматите също трябва да са цели. Морковите и целината също нарежете на едри парчета. След като джоланът е сварен го извадете от супата и го обезкостете. Обезкостеното месо, заедно със зеленчуците върнете обратно в бульона и варете до сваряване на зеленчуците.
Поднасяте супата гореща, като към всяка купичка добавяте нарязан пресен магданоз и бучка краве масло.', 2, 30, '2022/01/02', true, 'телешки джолан, моркови, домати, краве масло, оцет, картофи, целина, лук, черен пипер, магданоз', 1, 2),
       ('6', 'ОБЛАЧЕН ХЛЯБ', 'ЗАКУСКА', true, 10, 'Първата стъпка от рецептата за облачен хляб е подготвката на продуктите. Яйцата, трябва да са задължително на стайна температура. Разделяте ги белтъци и жълтъци. Разбивате белтъците с помощта на миксер или тел за разбиване на сняг. Може да добавите и шипка сол, ако вашето крема сирене не е особено солено. Разбивате до получаване на пухкава смес, добавяте и бакпулвера.
Жълтъците разбивате заедно с крема сиренето, докато се смесят добре. С помощта на шпатула или дървена лъжица добавяте жълтъците към белтъците, като правите това внимателно, за да остане сместа възможно най-пухкава.
В голяма тава поставяте хартия за печене, след което с помощта на супена лъжица изсипвате от сместа, като оставяте място между отделните хлебчета, тъй като бухват по време на печене. Печете в предварително загрята на 160 градуса фурна за около 20 минути. Хлебчетата са готови, когато се отделят лесно от хартията за печене без да залепват.', 0, 25, '2022/01/02', true, 'яйца, бакпулвер, крема сирене', 1, 1),
       ('7', 'ТЕЛЕШКО С ГРАХ', 'ОСНОВНО', false, 8, 'Първата стъпка от рецептата за телешко с грах е подготовката на телешкото месо. Измивате го, нарязвате го на едри кубчета. Почиствате и нарязвате на ситно лука, след което в тенджера сгорещявате олиото добавяте лука и месото и запържвате за около 3-4 минути, след което добавете вода, която добре да покрие месото и оставете на къкри на слаб огън за около час, докато се свари. От време на време разбърквайте и внимавайте месото да не остане без вода, ако е нужно долейте.
Почистете морковите, нарежете ги на ситно и ги добавете към телешкото. Идва моментът, в който трябва да добавите граха. Най-добре е да ползвате замразен грах, тъй като той увира най-бързо, но ако имате под ръка грах в буркани, то спокойно можете да използвате и него.
Добавете червения пипер и 1 чаена лъжица сол, добавете и почистените и нарязано на ситно домати, и оставете ястието да ври на слаб огън. След около 5-10 минути телешкото с грах ще е готово.', 2, 0, '2022/01/03', true, 'телешко месо, лук, домати, червен пипер, грах, моркови, олио', 1, 2),
       ('8', 'РОЛЦА ОТ ТИКВИЧКИ С КРЕМ СИРЕНЕ', 'ПРЕДЯСТИЕ', true, 8, 'Първата стъпка от рецептата за ролца от тиквички с крем сирене е изборът и подготовката на тиквичките. Избирате средно големи тиквички. Измивате ги, отрязвате двата им края и ги нарязвате на тънки филийки по дължина. На оребрен тиган, на скара или на грил изпичате тиквичките от двете страни, слагате ги в купа, като ги овкусявате с ½ чаена лъжица сол, зехтина и оцета.
Почиствате и счуквате скилидките чесън, почиствате и нарязвате копъра и орехите на ситно и изсипвате тези съставки в купа. Добавяте крем сиренето и разбърквате добре докато съставките се смесят добре.
В единия край на изпечените тиквени филийки слагате част от сместа, завивате на колелце, след което го забучвате с клечка за зъби, за да се фиксира и да не се развива.', 0, 30, '2022/02/19', true, 'тиквички, чесън, копър, зехтин, крем сирене, орехи, оцет', 1, 3),
       ('9', 'САЛАТА РОПОТАМО', 'САЛАТА', true, 10, 'Първата стъпка от рецептата за салата Ропотамо е подготовката на боба и чушките. Добруджанският шарен боб Статев подготвяте, както всеки път когато готвите боб. Заливате с вода и оставяте да престои за няколко часа или за една нощ. Изливате водата, наливате нова и слагате отново да заври, а след завиране я изсипвате и наливате нова. Варите до пълно сваряване. Може да ползвате и боб от консерва, така може да прескочите тази стъпка. Чушките изпичате, обелвате ги и ги нарязвате.
Измивате, почиствате и нарязвате на колелца морковите. В малка тенджера или касерола, изсипвате олиото, оцета, захарта и солта, и добавяте морковите, варите до омекване. Нарязвате киселите краставички на колелца, а чепе. В голяма купа изсипвате сварения и отцеден боб, нарязаните краставички и чушки, отцедения грах, морковите заедно с маринатата и доматеното пюре. Разбъркайте добре. По желание може да добавите и риба към салатата Ропотамо.
Салатата Ропотамо може да бъде сервирана и консумирана на момента, може да я съхранявате в хладилника за 3-4 дни, а може и да я сложите в бурканчета, които да стерилизирате за 15-20 минути.', 0, 30, '2022/02/19', true, 'боб, грах, моркови, оцет, сол, чушки, кисели краставички, доматено пюре, олио, захар', 1, 2),
       ('10', 'ДОМАТЕНА КРЕМ СУПА С ФИДЕ', 'СУПА', true, 4, 'Накълцайте на дребно лука, което може да направите и с блендер/кухненски робот. Накълцаният лук, заедно с натрошеното на ситно фиде запържвате в маслото, докато фидето придобие златист цвят.
След това нарежете на дребни парченца доматите, или за да стане още по-лесно и бързо ги пасирайте в блендер/кухненски робот. Ако желаете вашата доматена супа да стане съвсем гладка и доматите, с които разполагате са меки, може да ги обелите от ципите.След като пасирате доматите ги смесете с фидето и лука.
Тук е моменът да сложите сол на доматената супичка. И малко захар, за да не бъде много кисела. Накрая добавяте и млякото, което не трябва да е току-що излязло от хладилника, а да е леко затоплено, поне на стайна температура. И след само 15 мин варене имате чудесна  доматена крем-супа!', 0, 30, '2022/02/19', true, 'домати, фиде, захар, лук, прясно мляко', 1, 2),
       ('11', 'ПУХКАВ МНОГОЦВЕТЕН КРЕМ', 'КРЕМ', true, 12, 'Първата стъпка от рецептата за пухкав многоцветен крем е подготовката на кондензираното мляко. Трябва да варите кутията кондензирано мляко за около 2 часа, за да се сгъсти. Спокойно можете да направите това и предишния ден. А ако имате под ръка дулче де лече, използвайте него, вместо да варите кондензираното мляко. Докато варите кондензираното мляко, подгответе останалите продукти. Смилате какаовите бисквити и добавяте към тях кравето масло, което предварително разтопявате, разбърквате добре, за да се получат маслени трохи.
Може да ползвате, както пресни, така и замразени малини. Слагате ги в малка тенджера или касерола на котлона, добавяте захарта и ½ чаена чаша вода и разбърквате докато захарта се разтопи, след което оставяте на страна. Сладкарската сметана разбивате, според инструкциите на опаковката, добавяте и сиренето маскарпоне и отново разбърквате. В купа смесвате половината от сместа със сметаната с течния шоколад, а в друг съд разбърквате другата част със свареното кондензирано мляко.
Идва моментът, в който трябва да напълним кремчето в чашките. На дъното на чашките слагате пласт бисквитени трохи, които притискаме леко с лъжица. Върху бисквитената основа слагате от какаовия крем, следва пласт от малиновия сироп за свежест, а след това светлия крем с кондензирано мляко. Най-лесно пълненето на с помощта на пош. Поръсвате отново с какаовите бисквитени трохи. За финал, придаващ хрупкавост, добавяте и нарязаните на ситно лешници.', 0, 30, '2022/02/19', true, 'бисквити, мляко, малини, течен шоколад, лешници, краве масло, сладкарска сметана, захар, маскарпоне', 1, 4),
       ('12', 'ЛЕСЕН И ВКУСЕН КОЗУНАЧЕН КЕКС', 'КЕКС', true, 12, 'Първата стъпка от рецептата за лесен и вкусен козуначен кекс подготовката на маята. Затопляте леко млякото, добавяте в него 1 супена лъжица брашно, 1 супена лъжица захар, солта и маята и разбърквате. Оставяте на страна, за да се активира маята. Междувременно разтопявате кравето масло на водна баня и разбивате яйцата със захарта. След като маята шупне, добавяте към сместа яйцата и разтопеното масло.
Пресейте брашното. Локума нарежете на малки парченца. В в голяма купа към течната смес при непрекъснато бъркане добавете и брашното. Бъркайте до получаване на хомогенна смес, а накрая добавете и локума и разбъркайте добре с дървена лъжица, за да се разпредели в сместа.
Намажете форма за кекс с мазнина и изсипете готовата смес за козуначен кекс. Сложете кекса в студена фурна, след което я включете на 160 градуса. Така тестото ще втаса, докато фурната се загрява. Печете около 30-40 минути, до придобиване на апетитен златист цвят.', 1, 10, '2022/02/19', true, 'яйца, прясно мляко, мая, брашно, краве масло, захар, сол, локум', 1, 4),
       ('13', 'МАСЛЕН СЛАДКИШ С ЯБЪЛКИ И КРЕМ', 'СЛАДКИШ', true, 14, 'Първата стъпка от рецептата за маслен сладкиш с ябълки и крем е подготовката на тестото. Смесвате брашното, заедно с бакпулвера в купа, след което добавяте захарта, която сте разбили заедно с яйцата и ванилията. В купата добавяте и маста, и замесвате тесто. Спокойно, вместо свинска мас можете да използвате и краве масло. Готовото тесто приберете в хладилника, докато приготвяте двата вида плънка.
Кремът за масления сладкиш е крем ванилия. Трите жълтъка разбивате, заедно с брашното и 4-5 лъжици студено прясно мляко до получаване на гладка каша. Слагате  останалото мляко да заври, добавяте захарта и ванилията. Разбърквате докато се стопи захарта и дръпнете от котлона. С тел за разбиване, малко по малко на тънка струя при непрекъснато бъркане добавете сместа с брашното към горещото прясно мляко. След това върнете сместа на котлона на слаба степен, за да заври при непрекъснато бъркане. След като кремът заври за около 1-2 минути той ще се сгъсти достатъчно. Сложете капак на съда с крема и оставете на страни, за да изстине.
Ябълковата пълнка приготвяне, като оставите обелените и нарязани на ситно ябълки, заедно със захарта, канелата, кардамона и рома да заврат за няколко минути, като разбърквате от време на време. След като ябълките се сготвят, махате шушулките кардамон и пръчките канела, оставете сместа на страни, за да изстине.', 1, 0, '2022/02/19', true, 'свинска мас, захар, бакпулвер, прясно мляко, брашно, ябълки, ром, кардамон, яйца, ванилия, канела', 1, 4),
       ('14', 'ЛЕСНА РЕЦЕПТА ЗА ПИРОЖКИ', 'ЗАКУСКА', true, 5, 'Първата стъпка от рецептата за пирожки е подготовката на тестото. В голяма купа изсипвате водата, която трябва да е топла, но не вряща, захарта, солта, 3-4 супени лъжици брашно и маята и разбърквате добре, след което остаяте сместа на страни, за да се активира маята. Маята се е активирала, след като е удвоила или утроила обема си. Добавете към тази смес малко по малко останалото брашно, добавете и олиото и омесете гладко тесто. Намажете найлонова торбичка с олио и сложете тестото в нея, завържете я и оставете на топло, за да втаса тестото.
Докато тестото за пирожките втасва пригответе плънката за тях. Измийте картофите и ги сварете. Обелете лука и го нарежете на ситно, след което го запържете в сгорещеното олио. Варените картофи обелете, намачкайте ги, най-добре с преса за картофи и добавете към тях запържения лук, както и ½ чаена лъжица сол и  черния пипер. и разбъркайте.
От втасалото тесто оформете топки, всяка от които разточете на кръг. В едната половина сложете от сместа след което захлухлупете и притиснете крайчетата силно, даже е добре леко да ги завиете, за да се залепят двата края и да не излезе плънката по време на пържене. Пържите така оформените пирожки в сгорещената мазнина за около 2-3 минути от всяка страна, а след това поставяте готовите пирожки в чиния, в която предварително сте поставили домакинска хартия, за да се отцедят от излишната мазнина.', 1, 30, '2022/02/19', true, 'суха мая, захар, олио, картофи, брашно, сол, вода, лук, черен пипер', 1, 1),
       ('15', 'СПАГЕТИ С ДОМАТЕН СОС И ПРЕСЕН БОСИЛЕК', 'ПАСТА', true, 5, 'Добре е да изберете добре узрели домати, за да можете да ги обелите по-лесно. Ако обаче вашите домати са по-твърди, то направете по едно кръстче с ножа на всеки домат и ги потопете за няколко секунди във вряща вода. След това ще можете да ги обелите съвсем лесно. Обелените домати пюрирайте, или настържете на едро ренде. В сгорещено олио, или зехтин за готвене изсипете доматеното пюре и нарязания на много ситно чесън. Добавете и около една чаена лъжица сол. Доматеният сос трябва да ври докато се сгъсти. Накрая добавяте накъсаните на дребно листенца босилек. Разбира се, не е фатално ако нарежете босилеак, но се смята, че свежите подправки е добре да бъдат накъсани, за да се запази повече от аромата и хранителните им свойства. Разбира се, можете да ползвате и сух босилек, но пресният е много по-свеж и ароматен.
Спагетите сварете с щипка сол във вряща водя, следвайки инструкциите на пакета. Те винаги са най-точни. Но все пак (по стара българска традиция) не се предоверявайте и ги пробвайте, за да сте сигурни, че не са станали сурови.
Отцедете добре спагетите, поръсете с малко зехтин , разбъркайте и добавете към всяка порция от доматения сос. Отгоре сложете по няколко „люспички“ пармезан, или пък поръсете, ако ползвате настърган пармезан. Разбира се, можете да ползвате и кашкавал за поръсване, но пармезанът е много по-подходящ, защото е много по-твърд и при разбъркване не се слепва, както се получава, когато се ползва кашкавал.', 0, 30, '2022/02/19', true, 'домати, босилек, спагети, чесън, зехтин, пармезан', 1, 1),
       ('16', 'МАКАРОНЕНА САЛАТА С ПИЛЕ И АВОКАДО', 'ПАСТА', false, 5, 'Първата стъпка от рецептата за макаронена салата с пиле и авокадо е подготовката на пилето и макароните. Макароните сварявате след като ги изсипете във вряща подсолена вода и варите според указаното на пакета. Отцеждате ги от водата, заливате със студена вода и отново отцеждата, след което ги изсипвате в купа, добавяте малко зехтин и разбърквате, за да не се слепнат.
Докато варите и подготвяте макароните, сварявате и пилешкото месо. Може да ползвате всякакво пилешко месо - бяло месо, бутчета. След като месото те свари много добре го накъсвате на ситно и го добавяте към макароните.
Измивате лука и доматите и ги нарязвате на дребно. Авокадото също нарязвате на кубчета и ги добавяте в купата към макароните. Добавяте около ½ чаена лъжица сол както и предвиденото количество зехтин и балсамов оцет и разбърквате добре с лъжица, така че салатата да се овкуси добре.', 0, 30, '2022/02/19', true, 'макарони, авокадо, пресен лук, оцет, домати, пилешко месо, зехтин', 1, 2);

INSERT INTO `image` (`id`, `image_url`, `recipe_id`)
VALUES ('1', '/images/fit_1400_933.jpg', 1),
       ('2', '/images/fit_1400_934.jpg', 1),
       ('3', '/images/fit_1400_935.jpg', 1),
       ('4', '/images/fit_1400.jpg', 2),
       ('5', '/images/fit_1400_93.jpg', 2),
       ('6', '/images/fit_1400_9.jpg', 2),
       ('7', '/images/fit.jpg', 3),
       ('8', '/images/fit_1.jpg', 3),
       ('9', '/images/fit_2.jpg', 3),
       ('10', '/images/f.jpg', 4),
       ('11', '/images/fijpg.jpg', 4),
       ('12', '/images/fir.jpg', 4),
       ('13', '/images/teleshko.jpg', 5),
       ('14', '/images/teleshko2.jpg', 5),
       ('15', '/images/feee.jpg', 6),
       ('16', '/images/feejpg.jpg', 6),
       ('17', '/images/fe.jpg', 6),
       ('18', '/images/a.jpg', 7),
       ('19', '/images/aa.jpg', 7),
       ('20', '/images/aaa.jpg', 7),
       ('21', '/images/rolca.jpg', 8),
       ('22', '/images/rolca2.jpg', 8),
       ('23', '/images/ropotamo.jpg', 9),
       ('24', '/images/ropotamo2.jpg', 9),
       ('25', '/images/ropotamo3.jpg', 9),
       ('26', '/images/domatena.jpg', 10),
       ('27', '/images/domatena2.jpg', 10),
       ('28', '/images/krem.jpg', 11),
       ('29', '/images/krem2.jpg', 11),
       ('30', '/images/krem3.jpg', 11),
       ('31', '/images/keks.jpg', 12),
       ('32', '/images/keks2.jpg', 12),
       ('33', '/images/keks3.jpg', 12),
       ('34', '/images/sladkish.jpg', 13),
       ('35', '/images/sladkish2.jpg', 13),
       ('36', '/images/piroshki.jpg', 14),
       ('37', '/images/piroshki2.jpg', 14),
       ('38', '/images/pasta.jpg', 15),
       ('39', '/images/pasta2.jpg', 15),
       ('40', '/images/ps.jpg', 16),
       ('41', '/images/ps2.jpg', 16),
       ('42', '/images/ps3.jpg', 16);