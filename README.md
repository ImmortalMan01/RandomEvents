# RandomEvents Plugin

RandomEvents, Minecraft sunucuları için rastgele mini oyunlar ve turnuvalar düzenlemeye yarayan bir Spigot eklentisidir. Eklenti; Battle Royale, Spleef, TNT Run gibi pek çok oyun modunu otomatik olarak başlatıp yönetmenize olanak tanır. MySQL desteği, skorbord sistemi ve farklı sunucu eklentileriyle entegrasyon sağlayarak kapsamlı bir oyun deneyimi sunar.

## Özellikler
- Çok sayıda mini oyunu destekler (`BATTLE_ROYALE`, `SPLEEF`, `TNTRUN`, `HIDE_AND_SEEK` vb.).
- Rastgele zamanlarda etkinlik başlatmak için ayarlanabilir olasılık sistemi.
- Oyuncu sayısı sınırı, envanter yönetimi ve takım renkleri gibi gelişmiş maç seçenekleri.
- Turnuva modu: belirlenen sayıda tur ve ödül sistemi.
- PlaceholderAPI, CrackShot, WorldEdit ve Citizens gibi popüler eklentilerle uyumlu çalışır.
- İsteğe bağlı MySQL bağlantısıyla oyun istatistiklerini kaydetme.

## Kurulum
1. Projeyi indirin veya derlenmiş jar dosyasını `plugins/` klasörüne yerleştirin.
2. Spigot (veya türevi) sunucunuzu başlatın. İlk çalıştırmada `RandomEvents` klasöründe **config.yml** dosyası oluşacaktır.
3. `config.yml` içindeki ayarları ihtiyaçlarınıza göre düzenleyin. Örnek yapılandırmanın ilk bölümü aşağıdadır:

```yml
##################################################################
##                                              RandomEvents                   #
##                                              Configuration                  #
##################################################################
debugMode: false

##################################################################
## Wiki: https://github.com/Adri1711/RandomEventsWiki/wiki      ##
## Discord: https://discord.gg/rcXhBn68mQ                       ##
##################################################################
useEncoding: 'UTF-8'

##################################################################
##                                                GENERAL                      #
##################################################################
```

Tam yapılandırmadaki tüm ayarlar için dosyayı inceleyin veya [proje wiki'sine](https://github.com/Adri1711/RandomEventsWiki/wiki) göz atın.

## Derleme
Eklentiyi kaynaktan derlemek için sisteminizde Java 21 ve Gradle 8 yüklü olmalıdır. Ardından proje dizininde şu komutu çalıştırın:

```bash
gradle build
```

Başarılı bir derleme sonrasında jar dosyaları `RandomEvents/build/libs/` ve `RandomEvents_Quests/build/libs/` klasörlerinde oluşur.

## Komutlar
Eklenti iki ana komut sağlar:

- **/revent** – Komut menüsünü gösterir.
- **/randomevent** – Aynı işlevi görür.

Komutlar `plugin.yml` dosyasında tanımlanmıştır ve diğer bazı eklentiler isteğe bağlı olarak desteklenir:

```yml
name: RandomEvents
main: com.adri1711.randomevents.RandomEvents
version: 2.9.5
api-version: 1.21
# Removed hard dependency on Lib1711 as the required
# functionality is now bundled directly with the plugin
softdepend: [CrackShot, PlaceholderAPI, WorldEdit, WorldGuard, Multiworld, Multiverse-Core, NametagEdit, LibsDisguises, NoteBlockAPI, Citizens]
commands:
   revent:
      description: Shows the command menu too
      usage: /revent
   randomevent:
      description: Shows the command menu too
      usage: /randomevent
```

## Lisans
Bu proje MIT Lisansı ile yayınlanmıştır. Detaylar için `LICENSE.txt` dosyasına bakabilirsiniz.

## Katkıda Bulunma
Hataları bildirmek veya katkıda bulunmak için GitHub üzerinde pull request gönderebilirsiniz.
