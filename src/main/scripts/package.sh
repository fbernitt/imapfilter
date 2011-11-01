#!/bin/bash
#
# Creates a packaged version of imapfilter

MVNREPO="$HOME/.m2/repository"

# first compile
mvn install

TMPDIR=`mktemp -d`

LIBDIR="$TMPDIR/lib"

mkdir $LIBDIR

FILES=$(cat .classpath | grep entry | grep var | perl -pe "s/.* path=\"M2_REPO\/([^\"]+)\".*/\1/")

for FILE in $FILES ; do
    cp $MVNREPO/$FILE $LIBDIR/
done

FILTERJAR=$(find /home/fbe/.m2/repository/org/bernitt/imapfilter/imapfilter/1.0-SNAPSHOT/ -name "*.jar")
cp $FILTERJAR $LIBDIR/imapfilter.jar

cp src/main/scripts/imapfilter.sh $TMPDIR/

TARGETDIR=$(pwd)

pushd . &>/dev/null

cd $TMPDIR

tar -cjvf $TARGETDIR/imapfilter.tar.bz2 *

popd &>/dev/null

rm -Rf $TMPDIR

exit 0;
