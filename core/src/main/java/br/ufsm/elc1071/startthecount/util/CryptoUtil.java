package br.ufsm.elc1071.startthecount.util;

import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class CryptoUtil {

    private static final String ENCRYPTION_ALGORITHM = "Ed25519";

    public static boolean verifySignature(String content, String signature, byte[] publicKey) throws
        InvalidKeyException,
        SignatureException,
        IOException,
        NoSuchAlgorithmException,
        InvalidKeySpecException
    {
        BouncyCastleProvider provider = new BouncyCastleProvider();

        Security.addProvider(provider);

        SubjectPublicKeyInfo keyInfo = new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519), publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyInfo.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPTION_ALGORITHM, provider);
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature sig = Signature.getInstance(ENCRYPTION_ALGORITHM, provider);

        sig.initVerify(key);

        sig.update(Hex.decode(content));
        return sig.verify(Hex.decode(signature));
    }
}
